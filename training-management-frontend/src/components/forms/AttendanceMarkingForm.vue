<template>
  <div class="attendance-form">
    <div class="card">
      <div class="card-header">
        <h3>Mark Attendance</h3>
      </div>
      <div class="card-body">
        <form @submit.prevent="markAttendance">
          <div class="row">
            <div class="col-md-3">
              <div class="form-group mb-3">
                <label for="trainee" class="form-label">Trainee:</label>
                <select v-model="attendance.traineeId" class="form-select" id="trainee" required>
                  <option value="">Select a trainee</option>
                  <option v-for="trainee in trainees" :key="trainee.id" :value="trainee.id">
                    {{ trainee.name }}
                  </option>
                </select>
              </div>
            </div>
            <div class="col-md-3">
              <div class="form-group mb-3">
                <label for="batch" class="form-label">Batch:</label>
                <select v-model="attendance.batchId" class="form-select" id="batch" required>
                  <option value="">Select a batch</option>
                  <option v-for="batch in batches" :key="batch.id" :value="batch.id">
                    {{ batch.course }}
                  </option>
                </select>
              </div>
            </div>
            <div class="col-md-3">
              <div class="form-group mb-3">
                <label for="sessionDate" class="form-label">Session Date:</label>
                <input 
                  type="date" 
                  v-model="attendance.sessionDate" 
                  class="form-control" 
                  id="sessionDate" 
                  required 
                />
              </div>
            </div>
            <div class="col-md-3">
              <div class="form-group mb-3">
                <label for="status" class="form-label">Status:</label>
                <select v-model="attendance.status" class="form-select" id="status" required>
                  <option value="">Select status</option>
                  <option value="present">Present</option>
                  <option value="absent">Absent</option>
                </select>
              </div>
            </div>
          </div>
          <div class="d-grid">
            <button type="submit" class="btn btn-primary" :disabled="isSubmitting">
              {{ isSubmitting ? 'Marking...' : 'Mark Attendance' }}
            </button>
          </div>
        </form>

        <!-- Messages -->
        <div v-if="message" class="alert mt-3" :class="messageClass">
          {{ message }}
        </div>
      </div>
    </div>

    <!-- Display Attendance Records -->
    <div class="card mt-4">
      <div class="card-header">
        <h3>Attendance Records</h3>
      </div>
      <div class="card-body">
        <div class="table-responsive">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Trainee</th>
                <th>Batch</th>
                <th>Session Date</th>
                <th>Status</th>
                <th>Marked At</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="record in attendanceRecords" :key="record.id">
                <td>{{ record.trainee.name }}</td>
                <td>{{ record.batch.course }}</td>
                <td>{{ formatDate(record.sessionDate) }}</td>
                <td>
                  <span :class="record.status === 'present' ? 'badge bg-success' : 'badge bg-danger'">
                    {{ record.status }}
                  </span>
                </td>
                <td>{{ formatDateTime(record.markedAt) }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/services/api'

export default {
  name: 'AttendanceMarkingForm',
  data() {
    return {
      attendance: {
        traineeId: '',
        batchId: '',
        sessionDate: '',
        status: ''
      },
      trainees: [],
      batches: [],
      attendanceRecords: [],
      isSubmitting: false,
      message: '',
      messageClass: ''
    }
  },
  async mounted() {
    await this.loadData()
  },
  methods: {
    async loadData() {
      await Promise.all([
        this.loadTrainees(),
        this.loadBatches(),
        this.loadAttendanceRecords()
      ])
    },

    async loadTrainees() {
      try {
        const response = await api.get('/trainees')
        this.trainees = response.data
      } catch (error) {
        console.error('Error loading trainees:', error)
        this.showMessage('Error loading trainees', 'error')
      }
    },

    async loadBatches() {
      try {
        const response = await api.get('/batches')
        this.batches = response.data
      } catch (error) {
        console.error('Error loading batches:', error)
        this.showMessage('Error loading batches', 'error')
      }
    },

    async loadAttendanceRecords() {
      try {
        const response = await api.get('/attendance')
        this.attendanceRecords = response.data
      } catch (error) {
        console.error('Error loading attendance records:', error)
      }
    },

    async markAttendance() {
      this.isSubmitting = true
      try {
        await api.post('/attendance/mark', this.attendance)
        this.showMessage('Attendance marked successfully!', 'success')
        this.resetForm()
        await this.loadAttendanceRecords()
      } catch (error) {
        console.error('Error marking attendance:', error)
        this.showMessage('Error marking attendance', 'error')
      } finally {
        this.isSubmitting = false
      }
    },

    resetForm() {
      this.attendance = {
        traineeId: '',
        batchId: '',
        sessionDate: '',
        status: ''
      }
    },

    showMessage(message, type) {
      this.message = message
      this.messageClass = type === 'success' ? 'alert-success' : 'alert-danger'
      setTimeout(() => {
        this.message = ''
      }, 5000)
    },

    formatDate(dateString) {
      return new Date(dateString).toLocaleDateString()
    },

    formatDateTime(dateTimeString) {
      return new Date(dateTimeString).toLocaleString()
    }
  }
}
</script>

<style scoped>
.attendance-form {
  max-width: 1000px;
  margin: 0 auto;
}

.card {
  border: 1px solid #dee2e6;
  border-radius: 0.375rem;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
}

.card-header {
  padding: 0.75rem 1.25rem;
  background-color: #f8f9fa;
  border-bottom: 1px solid #dee2e6;
  border-radius: 0.375rem 0.375rem 0 0;
}

.card-body {
  padding: 1.25rem;
}
</style>
