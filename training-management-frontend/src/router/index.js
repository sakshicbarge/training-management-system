import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: () => import("../views/DashboardView.vue"),
    },
    {
      path: "/trainer-availability",
      name: "trainer-availability",
      component: () => import("../views/TrainerAvailabilityView.vue"),
    },
    {
      path: "/trainee-enrollment",
      name: "trainee-enrollment",
      component: () => import("../views/TraineeEnrollmentView.vue"),
    },
    {
      path: "/attendance",
      name: "attendance",
      component: () => import("../views/AttendanceView.vue"),
    },
  ],
});

export default router;
