import {useState} from 'react'
import './Sort.css';
const Sort = ({isOpen , onClose , setSortTime , setSortprice}) => {

    if(!isOpen){
        return null;
    }
    const Sorting = (sort) => {
        if(sort === true) setSortTime(); 
        else setSortprice();
        onClose();

    }
    return (
        <div>
            <button className = "departureTime" onClick = {() => {Sorting(true)}}>Departure Time</button>
            <button className = "Price" onClick = {() => {Sorting(false)}} >Pirce</button>
        </div>

    );






}
export default Sort;
