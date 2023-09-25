import MainLayout from 'Frontend/views/MainLayout.js';
import {createBrowserRouter, RouteObject} from 'react-router-dom';
import ParticipantView from "Frontend/views/ParticipantView";
import {LoginView} from "Frontend/views/LoginView";

export const routes: RouteObject[] = [
  {
    element: <MainLayout />,
    handle: { title: 'Main' },
    children: [
      { path: '/', element: <ParticipantView />, handle: { title: 'Participants' } },
    ],
  },
  {
    element: <LoginView />,
    path: '/login',
  }
];

export default createBrowserRouter(routes);
