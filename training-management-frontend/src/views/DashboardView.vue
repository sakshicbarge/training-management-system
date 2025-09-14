<template>
  <div class="dashboard">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <h1 class="mb-4">Training Management Dashboard</h1>
        </div>
      </div>

      <!-- Statistics Cards -->
      <div class="row mb-4">
        <div class="col-md-3">
          <div class="card stat-card bg-primary text-white">
            <div class="card-body">
              <div class="d-flex justify-content-between">
                <div>
                  <h4>{{ totalTrainers }}</h4>
                  <p class="mb-0">Total Trainers</p>
                </div>
                <i class="fas fa-chalkboard-teacher fa-2x"></i>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card stat-card bg-success text-white">
            <div class="card-body">
              <div class="d-flex justify-content-between">
                <div>
                  <h4>{{ totalBatches }}</h4>
                  <p class="mb-0">Total Batches</p>
                </div>
                <i class="fas fa-layer-group fa-2x"></i>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card stat-card bg-warning text-white">
            <div class="card-body">
              <div class="d-flex justify-content-between">
                <div>
                  <h4>{{ totalTrainees }}</h4>
                  <p class="mb-0">Total Trainees</p>
                </div>
                <i class="fas fa-user-graduate fa-2x"></i>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card stat-card bg-info text-white">
            <div class="card-body">
              <div class="d-flex justify-content-between">
                <div>
                  <h4>{{ totalAttendanceRecords }}</h4>
                  <p class="mb-0">Attendance Records</p>
                </div>
                <i class="fas fa-calendar-check fa-2x"></i>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Charts -->
      <div class="row">
        <div class="col-md-12">
          <div class="card">
            <div class="card-header">
              <h3>Trainer Availability vs Occupancy</h3>
            </div>
            <div class="card-body">
              <BarChart :chart-data="availabilityData" />
            </div>
          </div>
        </div>
      </div>
      <div class="row mt-4">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h3>Batch Enrollments</h3>
            </div>
            <div class="card-body">
              <PieChart :chart-data="enrollmentData" />
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h3>Attendance Trends</h3>
            </div>
            <div class="card-body">
              <LineChart :chart-data="attendanceData" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/services/api'
import BarChart from '@/components/charts/BarChart.vue'
import PieChart from '@/components/charts/PieChart.vue'
import LineChart from '@/components/charts/LineChart.vue'

export default {
  name: 'DashboardView',
  components: {
    BarChart,
    PieChart,
    LineChart
  },
  data() {
    return {
      totalTrainers: 0,
      totalBatches: 0,
      totalTrainees: 0,
      totalAttendanceRecords: 0,
      availabilityData: {},
      enrollmentData: {},
      attendanceData: {},
      loading: true
    }
  },
  async mounted() {
    await this.loadDashboardData()
  },
  methods: {
    async loadDashboardData() {
      try {
        this.loading = true
        const [
          trainersResponse,
          batchesResponse,
          traineesResponse,
          attendanceResponse,
          availabilityResponse,
          enrollmentResponse,
          attendanceTrendResponse
        ] = await Promise.all([
          api.get('/trainers'),
          api.get('/batches'),
          api.get('/trainees'),
          api.get('/attendance'),
          api.get('/reports/availability-occupancy'),
          api.get('/reports/batch-enrollments'),
          api.get('/reports/attendance-trends')
        ])

        // Set totals
        this.totalTrainers = trainersResponse.data.length
        this.totalBatches = batchesResponse.data.length
        this.totalTrainees = traineesResponse.data.length
        this.totalAttendanceRecords = attendanceResponse.data.length

        // Format chart data
        this.availabilityData = this.formatAvailabilityData(availabilityResponse.data)
        this.enrollmentData = this.formatEnrollmentData(enrollmentResponse.data)
        this.attendanceData = this.formatAttendanceData(attendanceTrendResponse.data)
        
      } catch (error) {
        console.error('Error loading dashboard data:', error)
        // Set default empty data for charts
        this.availabilityData = { labels: [], datasets: [] }
        this.enrollmentData = { labels: [], datasets: [] }
        this.attendanceData = { labels: [], datasets: [] }
      } finally {
        this.loading = false
      }
    },

    formatAvailabilityData(data) {
      if (!data || data.length === 0) {
        return { labels: ['No Data'], datasets: [{ label: 'No Data', data: [0], backgroundColor: '#ccc' }] }
      }
      
      return {
        labels: data.map(item => item.trainerName),
        datasets: [
          {
            label: 'Available Slots',
            backgroundColor: '#4caf50',
            data: data.map(item => item.available || 0)
          },
          {
            label: 'Assigned Slots',
            backgroundColor: '#f44336',
            data: data.map(item => item.assigned || 0)
          }
        ]
      }
    },

    formatEnrollmentData(data) {
      if (!data || data.length === 0) {
        return { labels: ['No Data'], datasets: [{ label: 'No Data', data: [0], backgroundColor: '#ccc' }] }
      }
      
      return {
        labels: data.map(item => item.batchName),
        datasets: [
          {
            label: 'Enrollments',
            backgroundColor: [
              '#42a5f5',
              '#66bb6a',
              '#ffca28',
              '#ef5350',
              '#ab47bc',
              '#26c6da',
              '#ff7043'
            ],
            data: data.map(item => item.enrollments || 0)
          }
        ]
      }
    },

    formatAttendanceData(data) {
      if (!data || data.length === 0) {
        return { 
          labels: ['No Data'], 
          datasets: [{ 
            label: 'No Data', 
            data: [0], 
            borderColor: '#ccc', 
            fill: false 
          }] 
        }
      }
      
      return {
        labels: data.map(item => this.formatDateLabel(item.date)),
        datasets: [
          {
            label: 'Daily Attendance',
            borderColor: '#42a5f5',
            backgroundColor: 'rgba(66, 165, 245, 0.1)',
            fill: true,
            tension: 0.4,
            data: data.map(item => item.presentCount || 0)
          }
        ]
      }
    },

    formatDateLabel(dateString) {
      const date = new Date(dateString)
      return date.toLocaleDateString('en-US', { month: 'short', day: 'numeric' })
    }
  }
}
</script>

<style scoped>
.dashboard {
  padding: 2rem;
  background-color: #f8f9fa;
  min-height: 100vh;
}

.stat-card {
  border: none;
  border-radius: 0.75rem;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
  transition: transform 0.2s;
}

.stat-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}

.card {
  border: none;
  border-radius: 0.75rem;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
}

.card-header {
  background-color: #f8f9fa;
  border-bottom: 1px solid #dee2e6;
  border-radius: 0.75rem 0.75rem 0 0 !important;
  font-weight: 600;
}

.card-body {
  padding: 1.5rem;
}

h1 {
  color: #495057;
  font-weight: 300;
  margin-bottom: 2rem;
}

h3 {
  color: #495057;
  font-size: 1.1rem;
  margin-bottom: 0;
}

.container-fluid {
  max-width: 1400px;
}

/* Responsive adjustments */
@media (max-width: 768px) {
  .dashboard {
    padding: 1rem;
  }
  
  .stat-card h4 {
    font-size: 1.5rem;
  }
  
  .stat-card p {
    font-size: 0.9rem;
  }
}
</style>
