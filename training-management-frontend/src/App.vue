<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="container">
        <router-link to="/" class="navbar-brand">
          <i class="fas fa-graduation-cap me-2"></i>
          Training Management
        </router-link>
        
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <router-link to="/" class="nav-link">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/dashboard" class="nav-link">Dashboard</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/trainer-availability" class="nav-link">Trainers</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/trainee-enrollment" class="nav-link">Trainees</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/attendance" class="nav-link">Attendance</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    
    <main>
      <RouterView />
    </main>
    
    <footer class="bg-light text-center py-3 mt-5">
      <div class="container">
        <p class="mb-0 text-muted">&copy; 2025 Training Management System</p>
      </div>
    </footer>
  </div>
</template>

<script>
import { RouterView } from 'vue-router'

export default {
  name: 'App',
  components: {
    RouterView
  }
}
</script>

<style>
#app {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.router-link-active {
  font-weight: bold;
}

body {
  margin: 0;
  padding: 0;
}
</style>
