import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { AdminLayout } from './components/layout/AdminLayout'
import { Dashboard } from './pages/admin/Dashboard'
import './index.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AdminLayout />}>
          <Route index element={<Dashboard />} />
          {/* Placeholders for future pages */}
          <Route path="guards" element={<div className="p-4">Guards Management (Phase 2)</div>} />
          <Route path="clients" element={<div className="p-4">Clients Management (Phase 2)</div>} />
          <Route path="sites" element={<div className="p-4">Sites & Geofences (Phase 3)</div>} />
          <Route path="incidents" element={<div className="p-4">Incidents & Alerts (Phase 5)</div>} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
