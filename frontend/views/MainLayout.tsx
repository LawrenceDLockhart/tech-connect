import { useRouteMetadata } from 'Frontend/util/routing';
import React, { Suspense, useState } from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import { AppLayout } from "@hilla/react-components/AppLayout";
import { DrawerToggle } from "@hilla/react-components/DrawerToggle";
import Placeholder from "Frontend/components/placeholder/Placeholder";
import { Button } from "@hilla/react-components/Button";
import { logout } from "@hilla/frontend";
import TechnologySelection, {Technologies} from "Frontend/components/TechnologySelection";
import MentorMenteeSelection, {MentorMentee} from "Frontend/components/MentorMenteeSelection";

const navLinkClasses = ({ isActive }: any) => {
    return `block rounded-m p-s ${isActive ? 'bg-primary-10 text-primary' : 'text-body'}`;
};

export default function MainLayout() {
    const currentTitle = useRouteMetadata()?.title ?? 'My App';
    const [selectedTechnologies, setSelectedTechnologies] = useState<Technologies[]>([]);
    const [mentorMenteeChoice, setMentorMenteeChoice] = useState<MentorMentee | null>(null);

    const handleSubmit = () => {
        // Handle saving the data to the backend or context.
        console.log(selectedTechnologies, mentorMenteeChoice);
    };

    return (
        <AppLayout primarySection="drawer">
            <div slot="drawer" className="flex flex-col justify-between h-full p-m">
                <header className="flex flex-col gap-m">
                    <h1 className="text-l m-0">My App</h1>
                    <nav>
                        <NavLink className={navLinkClasses} to="/">
                            Participants
                        </NavLink>
                    </nav>

                    <div className="mt-4">
                        <h3>Select Technologies</h3>
                        <TechnologySelection onChange={setSelectedTechnologies} />
                        <h3 className="mt-2">Are you a Mentor or Mentee?</h3>
                        <MentorMenteeSelection onChange={setMentorMenteeChoice} />
                        <button className="mt-2" onClick={handleSubmit}>Save</button>
                    </div>
                </header>
            </div>

            <div slot="navbar" className="flex gap-m items-center w-full">
                <DrawerToggle aria-label="Menu toggle"></DrawerToggle>
                <h2 className="text-l m-0 flex-grow">
                    {currentTitle}
                </h2>
                <Button className="pr-m" onClick={() => logout({ logoutUrl: "/" })}>
                    Logout
                </Button>
            </div>

            <Suspense fallback={<Placeholder />}>
                <Outlet />
            </Suspense>
        </AppLayout>
    );
}


