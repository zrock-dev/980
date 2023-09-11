'use client'

import FlightsSide from "@/components/booking/queue/FlightList";
import ListSide from "@/components/booking/queue/ListSide";
import {useState} from "react";
import '@/styles/booking/queue.css'

const Queue = () => {
    const [currentFlightId, setCurrentFlightId] = useState()

    const handleButtonClick = (flight) => {
        setCurrentFlightId(flight.id)
    };

    return (
        <div className="app-container">
            <div className="left-side-container">
                <h2>Ticket List</h2>
                <div className="side-content">
                    <ListSide currentFlightId={currentFlightId} />
                </div>
            </div>
            <div className="separator"></div>
            <div className="right-side-container">
                <h2>Flight List</h2>
                <div className="side-content">
                    <FlightsSide handleButtonClick={handleButtonClick} />
                </div>
            </div>
        </div>
    );
}

export default Queue