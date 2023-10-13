import React from 'react';

export enum MentorMentee {
    MENTOR = 'Mentor',
    MENTEE = 'Mentee'
}

const MentorMenteeSelection: React.FC<{ onChange: (choice: MentorMentee) => void }> = ({ onChange }) => {
    return (
        <div>
            {Object.values(MentorMentee).map(choice => (
                <label key={choice}>
                    <input type="radio" name="mentorMentee" value={choice} onChange={() => onChange(choice)} />
                    {choice}
                </label>
            ))}
        </div>
    );
};

export default MentorMenteeSelection;
