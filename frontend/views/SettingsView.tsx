import React, {useEffect, useState} from 'react'
import {useForm} from "@hilla/react-form";
import {useNavigate} from "react-router-dom";
import '/styles.css';
import {ParticipantService} from "Frontend/generated/endpoints";
import ParticipantDTOModel from "Frontend/generated/com/example/application/services/ParticipantDTOModel";
import {TextField} from "@hilla/react-components/TextField.js";
import {EmailField} from "@hilla/react-components/EmailField.js";
import {Button} from "@hilla/react-components/Button";
import {RadioGroup} from "@hilla/react-components/RadioGroup";
import {RadioButton} from "@hilla/react-components/RadioButton";


const SettingsView = () => {
    const navigate = useNavigate();
    const {model, field, read, submit} = useForm(ParticipantDTOModel,  {
        onSubmit: async (participant) => {
            const saved = await ParticipantService.save(participant);
            read(saved);

            navigate('/participants');
        }
    })

    useEffect(() => {
        readLoggedInUser();
    }, []);

    async function readLoggedInUser() {
        const loggedInUser = await ParticipantService.getLoggedInUser();
        read(loggedInUser);
    }

    return (
        <div className="p-m">
            <h3>Select Technologies</h3>
            <div className="flex flex-col gap-s items-start">
                <TextField label="User Name" {...field(model.userName)} />
                <EmailField label="Email" {...field(model.email)} />
                <RadioGroup label="Technologies" {...field(model.technologies)}>
                    <RadioButton value="python" label="Python" />
                    <RadioButton value="java" label="Java" />
                    <RadioButton value="javascript" label="Javascript"/>
                </RadioGroup>
                <RadioGroup label="Role" {...field(model.mentorOrMentee)}>
                    <RadioButton value="mentor" label="Mentor" />
                    <RadioButton value="mentee" label="Mentee" />
                </RadioGroup>
                <Button onClick={submit} theme="primary">Save</Button>
            </div>
        </div>
    )
}
export default SettingsView
