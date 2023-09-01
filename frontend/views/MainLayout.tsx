import { AppLayout } from '@hilla/react-components/AppLayout.js';
import React, { useEffect, useState } from 'react';
import { ParticipantEndpoint } from 'Frontend/generated/endpoints';
import Participant from 'Frontend/generated/com/example/application/domain/Participant';

export default function MainLayout() {
    const [data, setData] = useState<Participant[]>([]);

    useEffect(() => {
        fetchData();
    }, []);

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
          {/*<ParticipantView />*/}
          <AppLayout className="block h-full" primarySection="drawer">
                <h1 className="text-l m-0">Tech Connect</h1>
              {data.map((item: Participant) => (
                  <div key={item.id}>
                      <h2>{item.name}</h2>
                      <p>{item.email}</p>
                  </div>
              ))}
          </AppLayout>

      </div>
  );
}
