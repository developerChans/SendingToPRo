import React, {useState} from 'react';
import styled from 'styled-components';
import './css/home.css'
import SubjectList from '../components/SubjectList';
import { BiSearch } from 'react-icons/bi';

const Home = () => {
    const [input, setInput] =useState({
      name : ''
    });
    const {name} = input;
    const onChange = (e) => {
        const {value, name} = e.target;
        setInput({
            ...input,
            [name] : value
        });
    };
    const onReset = () => {
        setInput({
            name : ''
        });
    };

    return(
        <AllSection>
            <SearchContainer>
                <Icon><BiSearch size="35" /></Icon>
                <BoxSearch name="name" placeholder="검색어를 입력하세요. " onChange={onChange} value={name} />
            </SearchContainer>
            <Title>강의목록</Title>
            <Box1>
                <Text1>교수명</Text1>
            </Box1>
            <Box2>
                <Text2>과목</Text2>
            </Box2>
            
        </AllSection>
    );
}
/*
<SubjectList
                id = {1}
                subjectProfessor ={1}
                subjectName ={1}
                lectureIndex = {1}
            />
*/
const AllSection = styled.section`
    display: flex;
    align-items: center;
    margin-top: 20px;
    flex-direction: column;
    width: 1400px;
`;

const SearchContainer = styled.section`
    display: flex;
    flex-direction: row;
    width: 80%;
    margin: 20px;
    align-items: center;
`;

const BoxSearch = styled.input`
    display: flex;
    width: 1000px;
    height: 48px;
    border: 2px solid #383838; 
`;

const Title = styled.span`
    font-family: Roboto;
    font-style: normal;
    font-weight: bold;
    font-size: 18px;
    line-height: 21px;
    display: flex;
    margin-top: 20px;
    margin-right: 1200px;
    align-items: center;
    color: #000000;
`;

const Box1 = styled.div`
    width: 181px;
    height: 36px;
    left: 139px;
    top: 216px;

    background: #A17DD9;
`;

const Text1 = styled.span`
    display: flex;
    width: 60px;
    height: 21px;
    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    font-size: 18px;
    line-height: 21px;
    /* identical to box height */
    
    display: flex;
    align-items: center;
    text-align: center;
    
    color: #FFFFFF;
`;

const Box2 = styled.div`
    display :flex;
    width: 694px;
    height: 36px;
    align-items: center;
    background: #A17DD9;
`;

const Text2 = styled.span`
    display :flex;
    width: 40px;
    height: 21px;

    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    font-size: 18px;
    line-height: 21px;
    /* identical to box height */
    justifyContent: "center";
    display: flex;
    align-items: center;
    text-align: center;

    color: #FFFFFF;
`;

const Icon = styled.div`
    align-items: center;
    justify-content: center;
    display: flex;
    margin-right: 10px;
`;

export default Home;