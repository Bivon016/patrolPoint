/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        slate: {
          850: '#151e2e',
          900: '#0f172a',
        },
        brand: {
          primary: '#0284c7', // Sky 600
          secondary: '#38bdf8', // Sky 400
          accent: '#f59e0b', // Amber 500 for warnings
          success: '#10b981', // Emerald 500 for verified
        }
      }
    },
  },
  plugins: [],
}
