<template>
  <div class="chart-container">
    <canvas ref="chartCanvas"></canvas>
  </div>
</template>

<script>
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js'
import { Line } from 'vue-chartjs'

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
)

export default {
  name: 'LineChart',
  extends: Line,
  props: {
    chartData: {
      type: Object,
      required: true
    },
    options: {
      type: Object,
      default: () => ({
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: 'Attendance Trends'
          }
        },
        scales: {
          y: {
            beginAtZero: true
          }
        }
      })
    }
  },
  mounted() {
    this.renderChart(this.chartData, this.options)
  },
  watch: {
    chartData: {
      deep: true,
      handler(newData) {
        this.$data._chart.destroy()
        this.renderChart(newData, this.options)
      }
    }
  }
}
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 400px;
}
</style>
