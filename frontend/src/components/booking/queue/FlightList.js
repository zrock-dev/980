'use client'

import React, {useEffect, useState} from 'react';
import {requestAvailableFlights} from '@/backend/FlightsAPI';
import '@/styles/booking/flight/column-card-item.css'

const Card = ({ source, destination, handler, payload }) => {
    return (
        <button className="card-button" onClick={() => handler(payload)}>
            <div className="card-text">
                from {source}
            </div>
            <div className="destination">
                to {destination}
            </div>
        </button>
    );
};

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
                    <Card
                        source={element.source.name}
                        destination={element.destination.name}
                        handler={handleButtonClick}
                        payload={element}
                    />
                </div>
            ))}
        </div>
    );
};


export default Flights;
