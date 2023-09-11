'use client'

import React, {useEffect, useState} from 'react';
import {requestAvailableFlights} from '@/backend/FlightsAPI';

const Flights = ({handleButtonClick}) => {
    const [elements, setElements] = useState([]);

    useEffect(() => {
        fetchData()
            .catch(function (error) {
                console.error(error.message)
            })
    }, []);

    const fetchData = async () => {
        try {
            const elementsData = await requestAvailableFlights(0);
            setElements(elementsData);
        } catch (error) {
            console.error("Attempt to fetch flights failed with: :", error.message);
        }
    }

    return (
        <div>
            {elements.map((element, index) => (
                <div key={index}>
                    <button onClick={() => handleButtonClick(element)}>
                        from {element.source.name} to {element.destination.name}
                    </button>
                    <br/>
                    <br/>
                    <br/>
                </div>
            ))}
        </div>
    );
};


export default Flights;
