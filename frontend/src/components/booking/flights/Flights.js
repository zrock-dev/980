'use client'

import React, { useEffect, useState } from 'react';
import { requestAvailableFlights } from '@/backend/FlightsAPI';
import {useRouter} from "next/navigation";

const Flights = () => {
    const [elements, setElements] = useState([]);
    const router = useRouter();

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const elementsData = await requestAvailableFlights(0);
            setElements(elementsData);
        } catch (error) {
            console.error("Attempt to fetch flights failed with: :", error.message);
        }
    }

    const handleButtonClick = (element) => {
        if (element.available){
            router.push(`/booking?flight=${element.id}`)
        }
    };

    return (
        <div>
            {elements.map((element, index) => (
                <div key={index}>
                    <button onClick={() => handleButtonClick(element)}>
                        from {element.source.name} to {element.destination.name}
                    </button>
                    <br />
                    <br />
                    <br />
                    <br />
                </div>
            ))}
        </div>
    );
};


export default Flights;
