<template>
  <div class="availability-form">
    <div class="card">
      <div class="card-header">
        <h3>Add Trainer Availability</h3>
      </div>
      <div class="card-body">
        <form @submit.prevent="submitAvailability">
          <div class="form-group mb-3">
            <label for="trainer" class="form-label">Trainer:</label>
            <select v-model="form.trainerId" class="form-select" id="trainer" required>
              <option value="">Select a trainer</option>
              <option v-for="trainer in trainers" :key="trainer.id" :value="trainer.id">
                {{ trainer.name }} ({{ trainer.location }})
              </option>
            </select>
          </div>
          
          <div class="form-group mb-3">
            <label for="date" class="form-label">Date:</label>
            <input type="date" v-model="form.availableDate" class="form-control" id="date" required />
          </div>
          
          <div class="form-group mb-3">
            <label for="timeSlot" class="form-label">Time Slot:</label>
            <select v-model="form.timeSlot" class="form-select" id="timeSlot" required>
              <option value="">Select time slot</option>
              <option value="09:00-12:00">09:00-12:00</option>
              <option value="14:00-17:00">14:00-17:00</option>
            </select>
          </div>
          
          <div class="d-grid">
            <button type="submit" class="btn btn-primary" :disabled="isSubmitting">
              {{ isSubmitting ? 'Adding...' : 'Add Availability' }}
            </button>
          </div>
        </form>
        
        <!-- Success/Error Messages -->
        <div v-if="message" class="alert mt-3" :class="messageClass">
          {{ message }}
        </div>
      </div>
    </div>
    
    <!-- Display existing availability -->
    <div class="card mt-4">
      <div class="card-header">
        <h3>Current Availability</h3>
      </div>
      <div class="card-body">
        <div class="table-responsive">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Trainer</th>
                <th>Date</th>
                <th>Time Slot</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="availability in availabilities" :key="availability.id">
                <td>{{ availability.trainer.name }}</td>
                <td>{{ formatDate(availability.availableDate) }}</td>
                <td>{{ availability.timeSlot }}</td>
                <td>
                  <span :class="availability.isAssigned ? 'badge bg-danger' : 'badge bg-success'">
                    {{ availability.isAssigned ? 'Assigned' : 'Available' }}
                  </span>
                </td>
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
  name: 'TrainerAvailabilityForm',
  data() {
    return {
      form: {
        trainerId: '',
        availableDate: '',
        timeSlot: ''
      },
      trainers: [],
      availabilities: [],
      isSubmitting: false,
      message: '',
      messageClass: ''
    }
  },
  async mounted() {
    await this.loadTrainers()
    await this.loadAvailabilities()
  },
  methods: {
    async loadTrainers() {
      try {
        const response = await api.get('/trainers')
        this.trainers = response.data
      } catch (error) {
        console.error('Error loading trainers:', error)
        this.showMessage('Error loading trainers', 'error')
      }
    },
    
    async loadAvailabilities() {
      try {
        const response = await api.get('/trainers/availability')
        this.availabilities = response.data
      } catch (error) {
        console.error('Error loading availabilities:', error)
      }
    },
    
    async submitAvailability() {
      this.isSubmitting = true
      try {
        const trainer = this.trainers.find(t => t.id == this.form.trainerId)
        const payload = {
          trainer: trainer,
          availableDate: this.form.availableDate,
          timeSlot: this.form.timeSlot,
          isAssigned: false
        }
        
        await api.post('/trainers/availability', payload)
        this.showMessage('Availability added successfully!', 'success')
        this.resetForm()
        await this.loadAvailabilities()
      } catch (error) {
        console.error('Error adding availability:', error)
        this.showMessage('Error adding availability', 'error')
      } finally {
        this.isSubmitting = false
      }
    },
    
    resetForm() {
      this.form = {
        trainerId: '',
        availableDate: '',
        timeSlot: ''
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
.availability-form {
  max-width: 800px;
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
