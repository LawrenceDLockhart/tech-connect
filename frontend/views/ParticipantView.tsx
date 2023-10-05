import React, { useState, useEffect } from 'react';
import {ParticipantService} from "Frontend/generated/endpoints";
import Participant from "Frontend/generated/com/example/application/domain/Participant";
import ParticipantDTO from "Frontend/generated/com/example/application/services/ParticipantDTO";

const ParticipantView: React.FC = () => {
    const [participants, setParticipants] = useState<Participant[]>([]);

    const [data, setData] = useState<ParticipantDTO[]>([]);
    useEffect(() => {
        fetchData();
    }, []);
    const [showLogin, setShowLogin] = React.useState(true);


    async function fetchData() {
        try {
            const response = await ParticipantService.findAll();
            console.log("Response is ", response);
            setData(response);
        } catch (error) {
            console.error(error);
        }
    }
    return (
        <div className="App mx-auto">
            <h1>Mentor and Mentee List</h1>
                <h1 className="text-l m-0">Tech Connect</h1>
                {data.map((item) => (
                    <div key={item.id}>
                        <h2>{item.userName}</h2>
                        <p>{item.email}</p>
                    </div>
                ))}
        </div>
    );
}

export default ParticipantView;



