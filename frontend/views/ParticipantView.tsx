import React, { useState, useEffect } from 'react';
import {ParticipantService} from "Frontend/generated/endpoints";
import Participant from "Frontend/generated/com/example/application/domain/Participant";
import ParticipantDTO from "Frontend/generated/com/example/application/services/ParticipantDTO";
import {GridColumn} from "@hilla/react-components/GridColumn";
import {Grid} from "@hilla/react-components/Grid";
import '/styles.css';

const ParticipantView: React.FC = () => {
    const [participants, setParticipants] = useState<Participant[]>([]);

    const [data, setData] = useState<ParticipantDTO[]>([]);
    useEffect(() => {
        fetchData();
    }, []);
    const [showLogin, setShowLogin] = React.useState(true);
    const gridRef = React.useRef<any>(null);

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
            <h1 className="text-l m-0 text-center">Mentor Mentee List</h1>
            <Grid items={data}>
                <GridColumn header="User Name" path="userName" />
                <GridColumn header="Email" path="email" />
                <GridColumn header="Technologies" path="technologies" />
                <GridColumn header="Mentor/Mentee" path="mentorOrMentee" />
            </Grid>
        </div>
    );
}

export default ParticipantView;



