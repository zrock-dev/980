'use client'

import {useEffect, useState} from "react";
import {requestTickets} from "@/backend/FlightsAPI";

const Ticket = ({userId, priority}) => {
   return(
       <div>
           <div>
               User: {userId}
           </div>
           <div>
               priority: {priority}
           </div>
           <br/>
       </div>
   )
}

const ListSide = ({currentFlightId}) => {
    const [tickets, setTickets] = useState([{}])

    useEffect(() => {
        let promise = requestTickets(currentFlightId)
        if (promise){
            promise.then(function (data) {
                setTickets(data);
            })
        }
    }, [currentFlightId]);

    if (!currentFlightId){
        return (
            <div>
                Nice calves bro
            </div>
        )
    }

    return (
        <div>
            <h2>Ticket List</h2>
            <br/>
            {tickets.map((ticket) => (
                <Ticket key={ticket.id} userId={ticket.userId} priority={ticket.priority} />
            ))}
        </div>
    );
}

export default ListSide