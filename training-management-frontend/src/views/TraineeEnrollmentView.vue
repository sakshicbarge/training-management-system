<template>
  <div class="trainee-enrollment-view">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="mb-4">Trainee Enrollment Management</h1>
          <TraineeEnrollmentForm />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TraineeEnrollmentForm from '@/components/forms/TraineeEnrollmentForm.vue'

export default {
  name: 'TraineeEnrollmentView',
  components: {
    TraineeEnrollmentForm
  }
}
</script>

<style scoped>
.trainee-enrollment-view {
  padding: 2rem 0;
  background-color: #f8f9fa;
  min-height: 100vh;
}

h1 {
  color: #495057;
  font-weight: 300;
}
</style>
