'use client'

import {useEffect, useState} from "react";
import {requestTickets} from "@/backend/FlightsAPI";
import '../../../styles/booking/ticket.css'

const Ticket = ({userId, priority}) => {
    return (
        <div className="ticket">
            <div className="ticket-user">
                User ID: {userId}
            </div>
            <div className={`ticket-priority ${priority}`}>
                Priority: {priority}
            </div>
        </div>
    )
}

const ListSide = ({currentFlightId}) => {
    const [tickets, setTickets] = useState([{}])

    useEffect(() => {
        let promise = requestTickets(currentFlightId)
        if (promise) {
            promise.then(function (data) {
                setTickets(data);
            })
        }
    }, [currentFlightId]);

    if (!currentFlightId) {
        return (
            <div>
                Nice calves bro
            </div>
        )
    }

    return (
        <div>
            {tickets.map((ticket) => (
                <Ticket key={ticket.id} userId={ticket.userId} priority={ticket.priority}/>
            ))}
        </div>
    );
}

export default ListSide