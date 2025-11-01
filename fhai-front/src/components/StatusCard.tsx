import { LucideIcon } from "lucide-react";

interface StatusCardProps {
  title: string;
  value: string;
  subtitle?: string;
  change?: {
    value: string;
    positive?: boolean;
  };
  icon: LucideIcon;
  prefix?: string;
}

const StatusCard = ({
  title,
  value,
  subtitle,
  change,
  icon: Icon,
  prefix,
}: StatusCardProps) => {
  return (
    <div className="bg-white rounded-xl p-6 shadow-sm border border-gray-100 hover:shadow-md transition-shadow">
      <div className="flex items-start justify-between mb-4">
        <div className="flex-1">
          <p className="text-sm text-gray-500 mb-1">{title}</p>
          <div className="flex items-baseline gap-2">
            {prefix && <span className="text-sm text-gray-600">{prefix}</span>}
            <span className="text-3xl font-semibold text-gray-900">
              {value}
            </span>
          </div>
          {subtitle && <p className="text-xs text-gray-500 mt-1">{subtitle}</p>}
        </div>
        <div className="shrink-0">
          <Icon className="text-teal-500" size={24} />
        </div>
      </div>

      {change && (
        <div className="flex items-center gap-1 text-sm">
          <span
            className={`font-medium ${
              change.positive !== false ? "text-teal-600" : "text-red-500"
            }`}
          >
            {change.value}
          </span>
        </div>
      )}
    </div>
  );
};

export { StatusCard };
