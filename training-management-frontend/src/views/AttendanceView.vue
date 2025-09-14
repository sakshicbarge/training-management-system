<template>
  <div class="attendance-view">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="mb-4">Attendance Management</h1>
          <AttendanceMarkingForm />
        </div>
      </div>
    </div>
  </div>
</template><template>
  <div class="attendance-view">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="mb-4">Attendance Management</h1>
          <AttendanceMarkingForm />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AttendanceMarkingForm from '@/components/forms/AttendanceMarkingForm.vue'

export default {
  name: 'AttendanceView',
  components: {
    AttendanceMarkingForm
  }
}
</script>

<style scoped>
.attendance-view {
  padding: 2rem 0;
  background-color: #f8f9fa;
  min-height: 100vh;
}

h1 {
  color: #495057;
  font-weight: 300;
}
</style>


<script>
import AttendanceMarkingForm from '@/components/forms/AttendanceMarkingForm.vue'

export default {
  name: 'AttendanceView',
  components: {
    AttendanceMarkingForm
  }
}
</script>

<style scoped>
.attendance-view {
  padding: 2rem 0;
  background-color: #f8f9fa;
  min-height: 100vh;
}

h1 {
  color: #495057;
  font-weight: 300;
}
</style>
