import React from 'react';
import styled from 'styled-components';
import LoginBox from '../components/LoginBox';

const Login = () => {
  return(
    <>
      <Section>
        <Container>
          <LoginBox />
        </Container>
      </Section>
    </>
  )
}

const Section = styled.section`
  display: flex;
  flex: 1;
  align-items: flex-start;
  justify-content: center;
  background-color: #ECE8F2;
`;

const Container = styled.div`
  display: ;
  padding: 160px 0px;
`;

export default Login;
