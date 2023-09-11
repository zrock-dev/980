'use client'

import React from 'react';
import FlightList from "@/components/booking/utils/FlightList";
import {useRouter} from "next/navigation";

const FlightsManager = () => {
    const router = useRouter()

    const handleButtonClick = (element) => {
        if (element.available) {
            router.push(`/booking?flight=${element.id}`)
        }
    };

    return (
        <FlightList
            handleButtonClick={handleButtonClick}
        />
    );
};


export default FlightsManager;
