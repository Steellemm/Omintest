import { FC, useContext } from 'react'
import styles from './Body.module.scss'
import ButtonTest from '../../../components/buttons/button-test/ButtonTest.tsx'
import ButtonAddTest from '../../../components/buttons/button-add-test-content/ButtonAddTest.tsx'
import { Context } from '../../../main.tsx'


const Body: FC = () => {
    const { setIsShowPopup, isShowPopup } = useContext(Context)

    const { tests, setTests } = useContext(Context)

    const onClick = () => {
        setTests(tests)
        setIsShowPopup(!isShowPopup)
    }


    return (
        <div className={styles.body}>
            <ButtonAddTest onClick={onClick}/>
            {tests.map(test => <ButtonTest key={test.id} id={test.id} title={test.title}
                description={test.description}/>)}
        </div>
    )
}

export default Body
