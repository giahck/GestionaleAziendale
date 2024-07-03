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
    routeLink: 'ticket',
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
    routeLink: 'create-maschine',
    icon: 'fa fa-cogs',
    label: 'Machine',
    items: [
      {
        visibilita: 3,
        routeLink: 'create-maschine',
        label: 'Create Machine',
      },
      {
        routeLink: 'status-maschine',
        label: 'Status Machine',
      },
    ],
  },
  {
    
    routeLink: 'settings',
    icon: 'fa fa-cog',
    label: 'Settings',
  },
];
