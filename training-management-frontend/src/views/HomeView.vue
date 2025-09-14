<template>
  <div class="home">
    <div class="hero-section">
      <div class="container">
        <h1 class="display-4">Training Management System</h1>
        <p class="lead">Manage trainers, trainees, batches, and attendance efficiently</p>
        <router-link to="/dashboard" class="btn btn-primary btn-lg">View Dashboard</router-link>
      </div>
    </div>

    <div class="container mt-5">
      <div class="row">
        <div class="col-md-4">
          <div class="card feature-card">
            <div class="card-body text-center">
              <i class="fas fa-chalkboard-teacher fa-3x mb-3 text-primary"></i>
              <h5 class="card-title">Trainer Management</h5>
              <p class="card-text">Manage trainer availability and assignments</p>
              <router-link to="/trainer-availability" class="btn btn-outline-primary">
                Manage Trainers
              </router-link>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card feature-card">
            <div class="card-body text-center">
              <i class="fas fa-user-graduate fa-3x mb-3 text-success"></i>
              <h5 class="card-title">Trainee Enrollment</h5>
              <p class="card-text">Enroll trainees into batches</p>
              <router-link to="/trainee-enrollment" class="btn btn-outline-success">
                Manage Trainees
              </router-link>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card feature-card">
            <div class="card-body text-center">
              <i class="fas fa-calendar-check fa-3x mb-3 text-warning"></i>
              <h5 class="card-title">Attendance Tracking</h5>
              <p class="card-text">Mark and track attendance</p>
              <router-link to="/attendance" class="btn btn-outline-warning">
                Mark Attendance
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView'
}
</script>

<style scoped>
.hero-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 5rem 0;
  text-align: center;
}

.feature-card {
  border: none;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
  transition: transform 0.2s;
  margin-bottom: 2rem;
}

.feature-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}

.container {
  max-width: 1200px;
}
</style>
