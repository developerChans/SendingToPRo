import React from 'react';
import styled from 'styled-components';
import { css } from 'styled-jsx/css';
import { Link } from 'react-router-dom';

const Nav = () => {
  return(
    <NavContainer>
      <List ><Link to="/home" style = {{textDecoration: 'none', color: '#383838'}}>홈</Link></List>
      <List ><Link to="/book" style = {{textDecoration: 'none' , color: '#383838'}}>중고서적</Link></List>
      <List ><Link to="/lecture" style = {{textDecoration: 'none', color: '#383838'} }>강의목록</Link></List>
      <List ><Link to="/mypage" style = {{textDecoration: 'none', color: '#383838' }}>마이페이지</Link></List>
    </NavContainer>
  )
}

const List = styled.li`
  display: flex;
  width: 200px;
  height: 100%;
  align-items: center;
  justify-content: center;
  float: left;
  color: #383838;
  text-decoration : none;

  font-family: Roboto;
  font-style: normal;
  font-weight: bold;
  font-size: 20px;
  line-height: 23px;
  text-align: center;
`;

const NavContainer = styled.ul`
  display: flex;
  height: 100%;
  align-items: center;
  margin: 0;
  padding: 0;
`;

export default Nav;