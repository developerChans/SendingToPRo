import React from 'react';
import styled from 'styled-components';
import Nav from './Navigation';
import SignInButton from './SignInButton';
import SignUpButton from './SignUpButton';

const Header = () => {

    return (
        <Container>
            <HeaderLeft></HeaderLeft>
            <HeaderCenter>
                <Nav />
            </HeaderCenter>
            <HeaderRight><SignInButton /><SignUpButton /></HeaderRight>
        </Container>
    )
};

const Container = styled.header`
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 64px;
    align-items: center;
    padding: 12px 32px 12px 32px;
    background: white;
`;

const HeaderLeft = styled.section`
    display: flex;
    height: 100%;
    align-items: center;
    flex: 1;
    justify-content: flex-start;
`;

const HeaderCenter = styled.section`
    display: flex;
    height: 100%;
    align-items: center;
    flex: 3;
    justify-content: center;
`;

const HeaderRight = styled.section`
    display: flex;
    height: 100%;
    align-items: center;
    flex: 1;
    justify-content: center;
`;

export default Header;