import React from 'react';
import { Card, CardContent } from './Card';

export const StatCard = ({ title, value, icon: Icon, trend, trendLabel, colorClass = "text-brand-primary" }) => {
  return (
    <Card>
      <CardContent className="p-6">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-sm font-medium text-slate-500">{title}</h3>
          <div className={`p-2 rounded-lg bg-slate-50 ${colorClass}`}>
            <Icon size={20} />
          </div>
        </div>
        
        <div className="flex items-baseline gap-2">
          <span className="text-3xl font-bold text-slate-800">{value}</span>
        </div>
        
        {trend && (
          <div className="mt-2 flex items-center text-sm">
            <span className={`font-medium ${trend > 0 ? 'text-emerald-600' : trend < 0 ? 'text-rose-600' : 'text-slate-500'}`}>
              {trend > 0 ? '+' : ''}{trend}%
            </span>
            <span className="text-slate-400 ml-2">{trendLabel}</span>
          </div>
        )}
      </CardContent>
    </Card>
  );
};
