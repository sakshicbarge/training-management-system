<template>
  <div class="chart-container">
    <canvas ref="chartCanvas"></canvas>
  </div>
</template>

<script>
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  ArcElement
} from 'chart.js'
import { Pie } from 'vue-chartjs'

ChartJS.register(Title, Tooltip, Legend, ArcElement)

export default {
  name: 'PieChart',
  extends: Pie,
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
            text: 'Batch Enrollments'
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
