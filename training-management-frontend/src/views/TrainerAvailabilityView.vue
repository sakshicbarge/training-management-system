<template>
  <div class="trainer-availability-view">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="mb-4">Trainer Availability Management</h1>
          <TrainerAvailabilityForm />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TrainerAvailabilityForm from '@/components/forms/TrainerAvailabilityForm.vue'

export default {
  name: 'TrainerAvailabilityView',
  components: {
    TrainerAvailabilityForm
  }
}
</script>

<style scoped>
.trainer-availability-view {
  padding: 2rem 0;
  background-color: #f8f9fa;
  min-height: 100vh;
}

h1 {
  color: #495057;
  font-weight: 300;
}
</style>
