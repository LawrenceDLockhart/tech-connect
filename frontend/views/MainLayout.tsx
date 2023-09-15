import { AppLayout } from '@hilla/react-components/AppLayout.js';
import React, { useEffect, useState } from 'react';
import { ParticipantEndpoint } from 'Frontend/generated/endpoints';
import ParticipantDTO from 'Frontend/generated/com/example/application/domain/ParticipantDTO'
import ParticipantView from "Frontend/views/ParticipantView";
import {LoginForm} from "@hilla/react-components/LoginForm";
import Form from 'Frontend/components/Form';


/*
Make this the landing page with the login / signup options
*/

export default function MainLayout() {

    const [data, setData] = useState<ParticipantDTO[]>([]);
    useEffect(() => {
        fetchData();
    }, []);
    const [showLogin, setShowLogin] = React.useState(true);


    async function fetchData() {
        try {
            const response = await ParticipantEndpoint.findAll();
             console.log("Response is ", response);
            setData(response);
        } catch (error) {
            console.error(error);
        }
    }
  return (
      <div>
          <h1>Welcome to TechConnect!</h1>
          <p>The app to connect tech mentors and mentees glsobally</p>

          {showLogin ? <LoginForm /> : <Form />}

          <button onClick={() => setShowLogin(!showLogin)}>
              {showLogin ? "Need an account? Sign up!" : "Already have an account? Log in!"}
          </button>
          <AppLayout className="block h-full" primarySection="drawer">
                <h1 className="text-l m-0">Tech Connect</h1>
              {data.map((item) => (
                  <div key={item.id}>
                      <h2>{item.name}</h2>
                      <p>{item.email}</p>
                  </div>
              ))}
          </AppLayout>

      </div>
  );
}
