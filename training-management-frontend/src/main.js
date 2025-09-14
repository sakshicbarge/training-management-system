import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

// Import Bootstrap CSS and JS
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

// Import FontAwesome CSS (you can use CDN in index.html instead)
import "@fortawesome/fontawesome-free/css/all.min.css";

const app = createApp(App);

app.use(router);

app.mount("#app");
