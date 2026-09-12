import React from 'react';
import { StatCard } from '../../components/ui/StatCard';
import { Card, CardHeader, CardTitle, CardContent } from '../../components/ui/Card';
import { Users, MapPin, AlertTriangle, ShieldCheck } from 'lucide-react';
import { AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

// Mock data based on Phase 2 structure
const stats = [
  { title: "Active Guards", value: "42", icon: Users, trend: 5, trendLabel: "vs last week", colorClass: "text-brand-primary" },
  { title: "Active Sites", value: "18", icon: MapPin, trend: 0, trendLabel: "no change", colorClass: "text-indigo-600" },
  { title: "Verified Check-ins", value: "156", icon: ShieldCheck, trend: 12, trendLabel: "vs yesterday", colorClass: "text-brand-success" },
  { title: "Open Incidents", value: "3", icon: AlertTriangle, trend: -2, trendLabel: "vs last week", colorClass: "text-brand-accent" }
];

const checkinData = [
  { name: '08:00', verified: 40, missed: 2 },
  { name: '10:00', verified: 35, missed: 0 },
  { name: '12:00', verified: 42, missed: 1 },
  { name: '14:00', verified: 38, missed: 0 },
  { name: '16:00', verified: 45, missed: 3 },
  { name: '18:00', verified: 40, missed: 1 },
];

const recentActivity = [
  { id: 1, type: 'checkin', guard: 'John Smith', site: 'Downtown Office', time: '10 mins ago', status: 'verified' },
  { id: 2, type: 'incident', guard: 'Sarah Connor', site: 'Tech Campus', time: '25 mins ago', status: 'warning', detail: 'Unauthorized vehicle' },
  { id: 3, type: 'checkin', guard: 'Mike Johnson', site: 'Logistics Hub', time: '1 hour ago', status: 'verified' },
  { id: 4, type: 'missed', guard: 'David Chen', site: 'Retail Center', time: '2 hours ago', status: 'missed', detail: 'Missed scheduled check-in' },
];

export const Dashboard = () => {
  return (
    <div className="space-y-6">
      <div className="flex justify-between items-end">
        <div>
          <h1 className="text-2xl font-bold text-slate-800 tracking-tight">Overview</h1>
          <p className="text-slate-500 mt-1">Here's what's happening across all sites today.</p>
        </div>
        <div className="flex gap-3">
          <button className="px-4 py-2 bg-white border border-slate-200 text-slate-700 rounded-lg font-medium hover:bg-slate-50 transition-colors shadow-sm">
            Export Report
          </button>
          <button className="px-4 py-2 bg-brand-primary text-white rounded-lg font-medium hover:bg-brand-secondary transition-colors shadow-sm">
            Dispatch Guard
          </button>
        </div>
      </div>

      {/* Stats Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {stats.map((stat, i) => (
          <StatCard key={i} {...stat} />
        ))}
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Chart */}
        <Card className="lg:col-span-2">
          <CardHeader>
            <CardTitle>Check-in Volume Today</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="h-[300px] w-full">
              <ResponsiveContainer width="100%" height="100%">
                <AreaChart data={checkinData} margin={{ top: 10, right: 10, left: -20, bottom: 0 }}>
                  <defs>
                    <linearGradient id="colorVerified" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="5%" stopColor="#10b981" stopOpacity={0.1}/>
                      <stop offset="95%" stopColor="#10b981" stopOpacity={0}/>
                    </linearGradient>
                  </defs>
                  <CartesianGrid strokeDasharray="3 3" vertical={false} stroke="#e2e8f0" />
                  <XAxis dataKey="name" axisLine={false} tickLine={false} tick={{fill: '#64748b', fontSize: 12}} dy={10} />
                  <YAxis axisLine={false} tickLine={false} tick={{fill: '#64748b', fontSize: 12}} />
                  <Tooltip 
                    contentStyle={{ borderRadius: '8px', border: 'none', boxShadow: '0 4px 6px -1px rgb(0 0 0 / 0.1)' }}
                  />
                  <Area type="monotone" dataKey="verified" stroke="#10b981" strokeWidth={2} fillOpacity={1} fill="url(#colorVerified)" />
                </AreaChart>
              </ResponsiveContainer>
            </div>
          </CardContent>
        </Card>

        {/* Activity Feed */}
        <Card>
          <CardHeader>
            <CardTitle>Recent Activity</CardTitle>
          </CardHeader>
          <CardContent className="p-0">
            <div className="divide-y divide-slate-100">
              {recentActivity.map((activity) => (
                <div key={activity.id} className="p-4 hover:bg-slate-50 transition-colors flex gap-4">
                  <div className={`mt-1 h-2 w-2 rounded-full shrink-0 ${
                    activity.status === 'verified' ? 'bg-brand-success' :
                    activity.status === 'warning' ? 'bg-brand-accent' : 'bg-rose-500'
                  }`} />
                  <div>
                    <p className="text-sm font-medium text-slate-800">
                      {activity.type === 'incident' ? 'Incident Reported' : 
                       activity.type === 'missed' ? 'Missed Check-in' : 'Verified Check-in'}
                    </p>
                    <p className="text-sm text-slate-600 mt-0.5">
                      <span className="font-medium">{activity.guard}</span> at {activity.site}
                    </p>
                    {activity.detail && (
                      <p className="text-xs text-rose-600 mt-1 bg-rose-50 inline-block px-2 py-1 rounded">
                        {activity.detail}
                      </p>
                    )}
                    <p className="text-xs text-slate-400 mt-1">{activity.time}</p>
                  </div>
                </div>
              ))}
            </div>
            <div className="p-4 border-t border-slate-100 text-center">
              <button className="text-sm text-brand-primary font-medium hover:text-brand-secondary transition-colors">
                View all activity
              </button>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};
