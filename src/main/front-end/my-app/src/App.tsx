import React from 'react';
import logo from './logo.svg';

import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        {/* <img src={logo} className="App-logo" alt="logo" /> */}
        {/* <p> */}
          {/* Edit <code>src/App.tsx</code> and save to reload. */}
        {/* </p> */}
        {/* <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a> */}
      </header>

        <div className='container-fluid' id='side-bar'>
          <div className='row'>
            <h3>voltar</h3>
            <div>
              <h4>origem A</h4>
              <form action="text" method="post">
                <label htmlFor="origem">
                  <input type="text" />
                </label>
              </form>
            </div>
            <div>
              
              <form action="text" method="post">
                <label htmlFor="origem">
                  <input type="text" />
                </label>
              </form>
            </div>
            <div>
            <h4>origem B</h4>
              <form action="text" method="post">
                <label htmlFor="origem">
                  <input type="text" />
                </label>
              </form>
            </div>
            <div>
            
              <form action="text" method="post">
                <label htmlFor="origem">
                  <input type="text" />
                </label>
              </form>
                <div id='button-div'>
                  <form action="" method="post">
                    <label htmlFor="placeholder">
                      <input type="button" value=""  id='button-confirm'/>
                    </label>
                  </form>
                </div>

            </div>
            
          

          </div>
        </div>

        <div className='container-fluid' id='map-body'>
          <div className='container-fluid' id='footer'></div>
        </div>
    </div>
  );
}

export default App;
