import { useRouteMetadata } from 'Frontend/util/routing';
import React, { Suspense, useState } from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import { AppLayout } from "@hilla/react-components/AppLayout";
import { DrawerToggle } from "@hilla/react-components/DrawerToggle";
import Placeholder from "Frontend/components/placeholder/Placeholder";
import { Button } from "@hilla/react-components/Button";
import { logout } from "@hilla/frontend";

const navLinkClasses = ({ isActive }: any) => {
    return `block rounded-m p-s ${isActive ? 'bg-primary-10 text-primary' : 'text-body'}`;
};

export default function MainLayout() {
    const currentTitle = useRouteMetadata()?.title ?? 'My App';

    return (
        <AppLayout primarySection="drawer">
            <div slot="drawer" className="flex flex-col justify-between h-full p-m">
                <header className="flex flex-col gap-m">
                    <h1 className="text-l m-0">My App</h1>
                    <nav>
                        <NavLink className={navLinkClasses} to="/">
                            Settings
                        </NavLink>
                        <NavLink className={navLinkClasses} to="/participants">
                            Participants
                        </NavLink>
                    </nav>
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


