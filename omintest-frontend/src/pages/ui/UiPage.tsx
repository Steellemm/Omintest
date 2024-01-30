import { FC, useEffect, useState } from 'react'
import styles from './UiPage.module.scss'
import InputField from '../../components/inputs/input-field/InputField.tsx'
import InputFieldPass from '../../components/inputs/input-field-password/InputFieldPass.tsx'
import Dropdown from '../../components/dropdown/Dropdown.tsx'
import { IItemDropdown } from '../../types/components/dropdown/dropdown-props-type.ts'
import ButtonText from '../../components/buttons/button-text/ButtonText.tsx'
import Button from '../../components/buttons/button/Button.tsx'
import Search from '../../components/search/Search.tsx'
import ButtonTest from '../../components/buttons/button-test/ButtonTest.tsx'
import ButtonAddTest from '../../components/buttons/button-add-test-content/ButtonAddTest.tsx'
import Checkbox from '../../components/checkboxes/checkbox/Checkbox.tsx'
import Table from '../../components/table/Table.tsx'
import Accordion from '../../components/accordion/Accordion.tsx'
import ButtonProfile from '../../components/buttons/button-profile/ButtonProfile.tsx'
import SwitchGroup from '../../components/switch-group/SwitchGroup.tsx'

const UiPage: FC = () => {

    const [itemsDropdown, setItemsDropdown] = useState<IItemDropdown[]>()

    useEffect(() => {
        setItemsDropdown([
            { id: '1', title: 'Параметр 1' },
            { id: '2', title: 'Параметр 2' },
            { id: '3', title: 'Параметр 3' },
            { id: '4', title: 'Параметр 4' },
            { id: '5', title: 'Параметр 5' },
            { id: '6', title: 'Параметр 6' },
            { id: '7', title: 'Параметр 7' }
        ])
    }, [])


    return (
        <div className={styles.container}>
            <Button text={'Регистрация'} type={'over'}/>
            <Button text={'Регистрация'} type={'over'} disabled={true}/>
            <Button text={'Регистрация'} type={'over'} filled={true}/>
            <Button text={'Регистрация'} type={'over'} filled={true} disabled={true}/>

            <Search/>
            <Search small={true}/>
            <Dropdown items={itemsDropdown}/>
            <Dropdown items={itemsDropdown} error={true}/>

            <InputField title={'Электронная почта'} placeholder={'input'} onChange={() => this}/>
            <InputField title={'Электронная почта'} placeholder={'input'} active={true} onChange={() => this}/>
            <InputField title={'Электронная почта'} placeholder={'input'} checked={true} onChange={() => this}/>
            <InputField title={'Электронная почта'} placeholder={'input'} onChange={() => this}/>

            <InputFieldPass title={'Some Title'} onChange={() => this}/>
            <InputFieldPass title={'Some Title'} active={true} onChange={() => this}/>
            <InputFieldPass title={'Some Title'} checked={true} onChange={() => this}/>
            <InputFieldPass title={'Some Title'} onChange={() => this}/>


            <ButtonText onClick={() => this} text={'Забыли пароль?'}/>
            <ButtonText onClick={() => this} primary={true} text={'Забыли пароль?'}/>
            <ButtonAddTest onClick={() => this}/>
            <ButtonTest id='1' title='Тест №1' description='Описание'/>


            <Checkbox rounded={true} text={'Для регистрации подтвердите согласие на обработку персональных данных'} onChange={() => this}/>
            <Checkbox rounded={true} onChange={() => this}/>
            <Checkbox text={'Для регистрации подтвердите согласие на обработку персональных данных'} onChange={() => this}/>
            <Checkbox onChange={() => this}/>

            <Table/>
            <Accordion>
                контент аккордиона
            </Accordion>
            <ButtonProfile/>
            <SwitchGroup/>
        </div>
    )
}

export default UiPage
