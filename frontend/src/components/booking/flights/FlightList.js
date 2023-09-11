'use client'

import React, {useEffect, useState} from 'react';
import {requestAvailableFlights} from '@/backend/FlightsAPI';
import '@/styles/booking/flight/list-card-item.css'
import '@/styles/booking/flight/pagination-button.css'

const Card = ({source, destination, handler, payload}) => {
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

const FlightList = ({handleButtonClick}) => {
    const [elements, setElements] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        if (currentPage < 0){
            setCurrentPage(0)
            return;
        }

        if (currentPage > 15){
            setCurrentPage(15)
            return;
        }

        const fetchData = async () => {
            try {
                const elementsData = await requestAvailableFlights(currentPage);
                if (elementsData) {
                    setElements(elementsData);
                }
            } catch (error) {
                console.error("Attempt to fetch flights failed with: ", error.message);
            }
        };

        fetchData().catch(function (error) {
            console.error(error.message);
        });
    }, [currentPage]);

    const handleNextPage = () => {
        setCurrentPage((prevPage) => prevPage + 1);
    };

    const handlePrevPage = () => {
        setCurrentPage((prevPage) => prevPage - 1);
    };

    return (
        <div>
            <div className="card-container">
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
            <div className="pagination-buttons">
                <button
                    className="pagination-button"
                    onClick={handlePrevPage}
                    disabled={currentPage === 0}
                >
                    Prev
                </button>
                <button
                    className="pagination-button"
                    onClick={handleNextPage}
                    disabled={currentPage > 14}
                >
                    Next
                </button>
            </div>
        </div>
    );
};

export default FlightList
