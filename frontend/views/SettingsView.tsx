import React, {useEffect, useState} from 'react'
import {useForm} from "@hilla/react-form";

import {ParticipantService} from "Frontend/generated/endpoints";
import ParticipantDTOModel from "Frontend/generated/com/example/application/services/ParticipantDTOModel";
import {TextField} from "@hilla/react-components/TextField.js";
import {EmailField} from "@hilla/react-components/EmailField.js";
import {Button} from "@hilla/react-components/Button";
import {Checkbox} from "@hilla/react-components/Checkbox";
import {CheckboxGroup} from "@hilla/react-components/CheckboxGroup";

const SettingsView = () => {

    const {model, field, read, submit} = useForm(ParticipantDTOModel,  {
        onSubmit: async (participant) => {
            const saved = await ParticipantService.save(participant);
            read(saved);
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
                <CheckboxGroup
                    label="MentorMentee"
                >
                    <Checkbox value="mentor" label="mentor" />
                    <Checkbox value="mentee" label="mentee" />
                </CheckboxGroup>
                <Button onClick={submit} theme="primary">Save</Button>
            </div>
        </div>
    )
}
export default SettingsView
