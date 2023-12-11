import React, {Component} from 'react';
import {connect} from "react-redux";
import {handleValidate} from "../scripts/Validation";

class ScrumHome extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        handleValidate()
    }

    render() {
        const retrospectives = this.props.scrumData["retrospectives"]
        return (
        <div className="well">
            <legend style={{paddingBottom: 40}}>
                <span style={{marginLeft: 30}}>
                    <a className="btn btn-primary" href="/scrum/create/retrospective">
                        Add Scrum Data
                    </a>
                </span>
            </legend>
            {retrospectives != undefined && retrospectives.length > 0 &&
               <legend style={{paddingBottom: 40}}>
                    <span style={{marginLeft: 30}}>
                        <a className="btn btn-primary" href="/scrum/view/retrospective">
                            View Scrum Data
                        </a>
                    </span>
               </legend>
            }
        </div>)
    }
}

const mapStateToProps = state => {
    return {
        scrumData: state.scrumData
    }
}

export default connect(mapStateToProps)(ScrumHome)