import { InavBarData } from "./inav-bar-data.interface";

export const navbarData: InavBarData[]= [
  {
    routeLink: '',
    icon: 'fa fa-home',
    label: 'Home',
  },
  {
    routeLink: 'chat-tiket',
    icon: 'fa fa-tools',
    label: 'Chat-Tiket',
  },
  {
    routeLink: 'tickets',
    icon: 'fa fa-ticket',
    label: 'Tickets',
    items: [
      {
        routeLink: 'ticket',
        label: 'Tickets',
      },
      {
        routeLink: 'create-tickets',
        label: 'Create Tickets',
      }
    ],
  },
  {
    routeLink: 'maschines',
    icon: 'fa fa-cogs',
    label: 'Maschine',
    items: [
      {
        visibilita: 3,
        routeLink: 'create-maschine',
        label: 'Create Maschine',
      },
      {
        routeLink: 'status-maschine',
        label: 'Status Maschine',
      },
    ],
  },
  {
    
    routeLink: 'settings',
    icon: 'fa fa-cog',
    label: 'Settings',
  },
];
