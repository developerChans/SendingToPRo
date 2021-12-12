import React, {useMemo, useState} from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';

const SubjectList = ({id, subjectProfessor, subjectName, lectureIndex}) => {
    /*
    const columnData = [
        {
            accessor : 'subjectProfessor',
            Header : '교수명'
        },
        {
            accessor : 'subjectName',
            Header : '과목명'
        }
    ];


    const [info, setInfo] = useState();
    const getDatas = () => {
        data.getDatas().then(item => setInfo(item));
    };
    const data = useMemo(() => info, [info]);
    const Table = ({columns, data}) => {
        const {getTableProps, getTableBodyProps, headerGroups, rows, prepareRow} =
        useTable({columns, data});
    }
    return (

    );
    */
};

const Box1 = styled.div`
    display:flex
    left: 0%;
    right: 79.89%;
    top: 3.23%;
    bottom: 3.23%;
    
    background: #FFFFFF;
`
const Text1 = styled.span`
    display:flex
    left: 0.57%;
    right: 80.23%;
    top: 0%;
    bottom: 0%;

    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    font-size: 16px;
    line-height: 19px;
    display: flex;
    align-items: center;
    text-align: center;

    color: #000000;
`
const Box2 = styled.div`
    display:flex
    left: 21.15%;
    right: 0%;
    top: 6.45%;
    bottom: 0%;

    background: #FFFFFF;
`

const Text2 = styled.span`
    display:flex;
    left: 22.64%;
    right: 32.18%;
    top: 9.68%;
    bottom: -6.45%;

    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    font-size: 16px;
    line-height: 19px;
    display: flex;
    align-items: center;

    color: #000000;
`

const Button1 = styled.button`
    display : flex;
    left: 80.11%;
    right: 5.75%;
    top: 16.13%;
    bottom: 12.9%;

    background: #FFFFFF;
    border: 1px solid #000000;
    box-sizing: border-box;
    box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.25);
    border-radius: 8px;
`
export default SubjectList;