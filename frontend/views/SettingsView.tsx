import React, {useEffect, useState} from 'react'
import {useForm} from "@hilla/react-form";
import {useNavigate} from "react-router-dom";

import ParticipantView from 'Frontend/views/ParticipantView';
import {ParticipantService} from "Frontend/generated/endpoints";
import ParticipantDTOModel from "Frontend/generated/com/example/application/services/ParticipantDTOModel";
import {TextField} from "@hilla/react-components/TextField.js";
import {EmailField} from "@hilla/react-components/EmailField.js";
import {Button} from "@hilla/react-components/Button";
import {Checkbox} from "@hilla/react-components/Checkbox";
import {CheckboxGroup} from "@hilla/react-components/CheckboxGroup";
import {RadioGroup} from "@hilla/react-components/RadioGroup";
import {RadioButton} from "@hilla/react-components/RadioButton";
import Technology from "Frontend/generated/com/example/application/domain/Technology";

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
                <CheckboxGroup
                    label="Technologies"
                >
                    <Checkbox value="python" label="Python" {...field(model.technologies)}/>
                    <Checkbox value="java" label="Java" {...field(model.technologies)}/>
                    <Checkbox value="javascript" label="Javascript" {...field(model.technologies)}/>
                </CheckboxGroup>
                <CheckboxGroup {...field(model.technologies)}>
                    {Object.values(Technology).map(technology => (
                        <RadioButton key={technology} value={technology} label={technology} />
                    ))}
                </CheckboxGroup>
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
