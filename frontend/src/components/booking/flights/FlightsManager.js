'use client'

import React from 'react';
import {useRouter} from "next/navigation";
import FlightList from "@/components/booking/flights/FlightList";

const FlightsManager = () => {
    const router = useRouter()

    const handleButtonClick = (element) => {
        // if (element.available) {
        //     router.push(`/booking?flight=${element.id}`)
        // }
    };

    return (
        <FlightList
            handleButtonClick={handleButtonClick}
        />
    );
};


export default FlightsManager;
