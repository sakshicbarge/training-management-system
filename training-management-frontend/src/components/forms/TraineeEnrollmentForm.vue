<template>
  <div class="enrollment-form">
    <div class="card">
      <div class="card-header">
        <h3>Trainee Enrollment</h3>
      </div>
      <div class="card-body">
        <!-- New Trainee Form -->
        <div class="mb-4">
          <h4>Create New Trainee</h4>
          <form @submit.prevent="createTrainee">
            <div class="row">
              <div class="col-md-6">
                <div class="form-group mb-3">
                  <label for="traineeName" class="form-label">Name:</label>
                  <input 
                    type="text" 
                    v-model="newTrainee.name" 
                    class="form-control" 
                    id="traineeName" 
                    required 
                  />
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group mb-3">
                  <label for="traineeEmail" class="form-label">Email:</label>
                  <input 
                    type="email" 
                    v-model="newTrainee.email" 
                    class="form-control" 
                    id="traineeEmail" 
                    required 
                  />
                </div>
              </div>
            </div>
            <button type="submit" class="btn btn-success" :disabled="isCreating">
              {{ isCreating ? 'Creating...' : 'Create Trainee' }}
            </button>
          </form>
        </div>

        <!-- Enrollment Form -->
        <div>
          <h4>Enroll Trainee in Batch</h4>
          <form @submit.prevent="enrollTrainee">
            <div class="row">
              <div class="col-md-6">
                <div class="form-group mb-3">
                  <label for="trainee" class="form-label">Trainee:</label>
                  <select v-model="enrollment.traineeId" class="form-select" id="trainee" required>
                    <option value="">Select a trainee</option>
                    <option v-for="trainee in trainees" :key="trainee.id" :value="trainee.id">
                      {{ trainee.name }} ({{ trainee.email }})
                    </option>
                  </select>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group mb-3">
                  <label for="batch" class="form-label">Batch:</label>
                  <select v-model="enrollment.batchId" class="form-select" id="batch" required>
                    <option value="">Select a batch</option>
                    <option v-for="batch in batches" :key="batch.id" :value="batch.id">
                      {{ batch.course }} - {{ batch.location }}
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <button type="submit" class="btn btn-primary" :disabled="isEnrolling">
              {{ isEnrolling ? 'Enrolling...' : 'Enroll Trainee' }}
            </button>
          </form>
        </div>

        <!-- Messages -->
        <div v-if="message" class="alert mt-3" :class="messageClass">
          {{ message }}
        </div>
      </div>
    </div>

    <!-- Display Enrollments -->
    <div class="card mt-4">
      <div class="card-header">
        <h3>Current Enrollments</h3>
      </div>
      <div class="card-body">
        <div class="table-responsive">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Trainee</th>
                <th>Email</th>
                <th>Batch</th>
                <th>Course</th>
                <th>Location</th>
                <th>Enrollment Date</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="trainee in enrolledTrainees" :key="trainee.id">
                <td>{{ trainee.name }}</td>
                <td>{{ trainee.email }}</td>
                <td>{{ trainee.batch ? trainee.batch.course : 'Not enrolled' }}</td>
                <td>{{ trainee.batch ? trainee.batch.course : '-' }}</td>
                <td>{{ trainee.batch ? trainee.batch.location : '-' }}</td>
                <td>{{ formatDate(trainee.enrollmentDate) }}</td>
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
  name: 'TraineeEnrollmentForm',
  data() {
    return {
      newTrainee: {
        name: '',
        email: ''
      },
      enrollment: {
        traineeId: '',
        batchId: ''
      },
      trainees: [],
      batches: [],
      enrolledTrainees: [],
      isCreating: false,
      isEnrolling: false,
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
        this.loadBatches()
      ])
    },

    async loadTrainees() {
      try {
        const response = await api.get('/trainees')
        this.trainees = response.data
        this.enrolledTrainees = response.data
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

    async createTrainee() {
      this.isCreating = true
      try {
        await api.post('/trainees', this.newTrainee)
        this.showMessage('Trainee created successfully!', 'success')
        this.newTrainee = { name: '', email: '' }
        await this.loadTrainees()
      } catch (error) {
        console.error('Error creating trainee:', error)
        this.showMessage('Error creating trainee', 'error')
      } finally {
        this.isCreating = false
      }
    },

    async enrollTrainee() {
      this.isEnrolling = true
      try {
        await api.post('/trainees/enroll', this.enrollment)
        this.showMessage('Trainee enrolled successfully!', 'success')
        this.enrollment = { traineeId: '', batchId: '' }
        await this.loadTrainees()
      } catch (error) {
        console.error('Error enrolling trainee:', error)
        this.showMessage('Error enrolling trainee', 'error')
      } finally {
        this.isEnrolling = false
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
    }
  }
}
</script>

<style scoped>
.enrollment-form {
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
