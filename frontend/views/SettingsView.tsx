import React, {useEffect, useState} from 'react'
import {useForm} from "@hilla/react-form";

import {ParticipantService} from "Frontend/generated/endpoints";
import ParticipantDTOModel from "Frontend/generated/com/example/application/services/ParticipantDTOModel";
import {TextField} from "@hilla/react-components/TextField.js";
import {EmailField} from "@hilla/react-components/EmailField.js";
import {RadioGroup} from "@hilla/react-components/RadioGroup";
import {Button} from "@hilla/react-components/Button";
import {RadioButton} from "@hilla/react-components/RadioButton";
import Technology from "Frontend/generated/com/example/application/domain/Technology";

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

                <RadioGroup {...field(model.technologies)}>
                    {Object.values(Technology).map(technology => (
                        <RadioButton key={technology} value={technology} label={technology} />
                    ))}
                </RadioGroup>

                <Button onClick={submit} theme="primary">Save</Button>
            </div>
        </div>
    )
}
export default SettingsView
