import React from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import { LayoutDashboard, Users, Building2, MapPin, AlertTriangle, Bell, Search, Settings } from 'lucide-react';

export const AdminLayout = () => {
  const navItems = [
    { label: 'Dashboard', icon: LayoutDashboard, path: '/' },
    { label: 'Guards', icon: Users, path: '/guards' },
    { label: 'Clients', icon: Building2, path: '/clients' },
    { label: 'Sites', icon: MapPin, path: '/sites' },
    { label: 'Incidents', icon: AlertTriangle, path: '/incidents' },
  ];

  return (
    <div className="flex h-screen bg-slate-50 overflow-hidden">
      {/* Sidebar */}
      <aside className="w-64 bg-slate-900 text-slate-300 flex flex-col">
        <div className="p-6 flex items-center gap-3">
          <div className="w-8 h-8 rounded bg-brand-primary flex items-center justify-center">
            <span className="text-white font-bold text-xl leading-none">P</span>
          </div>
          <span className="text-white text-xl font-bold tracking-tight">PatrolPoint</span>
        </div>
        
        <nav className="flex-1 px-4 mt-6 space-y-1">
          {navItems.map((item) => (
            <NavLink
              key={item.path}
              to={item.path}
              className={({ isActive }) =>
                `flex items-center gap-3 px-3 py-2.5 rounded-lg transition-colors ${
                  isActive 
                    ? 'bg-slate-800 text-white' 
                    : 'hover:bg-slate-800/50 hover:text-white'
                }`
              }
            >
              <item.icon size={20} />
              <span className="font-medium">{item.label}</span>
            </NavLink>
          ))}
        </nav>
        
        <div className="p-4 mt-auto">
          <button className="flex items-center gap-3 px-3 py-2.5 w-full rounded-lg hover:bg-slate-800/50 hover:text-white transition-colors">
            <Settings size={20} />
            <span className="font-medium">Settings</span>
          </button>
        </div>
      </aside>

      {/* Main Content */}
      <div className="flex-1 flex flex-col min-w-0 overflow-hidden">
        {/* Header */}
        <header className="h-16 bg-white border-b border-slate-200 flex items-center justify-between px-8 z-10">
          <div className="flex items-center text-slate-400">
            <Search size={20} className="absolute ml-3" />
            <input 
              type="text" 
              placeholder="Search..." 
              className="pl-10 pr-4 py-2 bg-slate-50 border-none rounded-lg focus:ring-2 focus:ring-brand-primary outline-none w-64 transition-all"
            />
          </div>
          
          <div className="flex items-center gap-6">
            <button className="relative text-slate-400 hover:text-slate-600 transition-colors">
              <Bell size={20} />
              <span className="absolute top-0 right-0 w-2 h-2 bg-brand-accent rounded-full border-2 border-white"></span>
            </button>
            <div className="h-8 w-8 rounded-full bg-brand-primary text-white flex items-center justify-center font-semibold text-sm cursor-pointer shadow-sm">
              AD
            </div>
          </div>
        </header>

        {/* Page Content */}
        <main className="flex-1 overflow-y-auto p-8">
          <Outlet />
        </main>
      </div>
    </div>
  );
};
