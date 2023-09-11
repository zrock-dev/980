'use client'

import FlightsSide from "@/components/booking/utils/FlightList";
import ListSide from "@/components/booking/queue/ListSide";
import {useState} from "react";

const Queue = () => {
    const [currentFlightId, setCurrentFlightId] = useState()

    const handleButtonClick = (flight) => {
        setCurrentFlightId(flight.id)
    };

    return (
        <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center'}}>
            <div style={{flex: 1}}>
                <ListSide
                    currentFlightId={currentFlightId}
                />
            </div>
            <div style={{flex: 1, borderLeft: '1px solid black', padding: '10px'}}>
                <FlightsSide
                    handleButtonClick={handleButtonClick}
                />
            </div>
        </div>
    );
}

export default Queue