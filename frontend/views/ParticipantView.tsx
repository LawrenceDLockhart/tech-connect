import React, { useState, useEffect } from 'react';

interface Participant {
    id: number;
    name: string;
    email: string;
    mentorId: number | null;
    technology: string;
}

const ParticipantView: React.FC = () => {
    const [participants, setParticipants] = useState<Participant[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/")
            .then((res) => res.json())
            .then((data) => setParticipants(data))
            .catch((error) => console.log("Fetching participants failed: ", error));
    }, []);

    const mentors = participants.filter((participant) => participant.mentorId === null);
    const mentees = participants.filter((participant) => participant.mentorId !== null);

    return (
        <div className="App">
            <h1>Mentor and Mentee List</h1>

            <div>
                <h2>Mentors</h2>
                <ul>
                    {mentors.map((mentor) => (
                        <li key={mentor.id}>
                            {mentor.name} ({mentor.email}) - Technology: {mentor.technology}
                        </li>
                    ))}
                </ul>
            </div>

            <div>
                <h2>Mentees</h2>
                <ul>
                    {mentees.map((mentee) => (
                        <li key={mentee.id}>
                            {mentee.name} ({mentee.email}) - Technology: {mentee.technology}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default ParticipantView;



