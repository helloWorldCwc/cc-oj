/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { JudgeCase } from './JudgeCase';
import type { JudgeConfig } from './JudgeConfig';

export type QuestionInfoUpdateRequest = {
    acceptedNum?: number;
    acceptedRate?: number;
    answer?: string;
    content?: string;
    id?: number;
    judgeCaseList?: Array<JudgeCase>;
    judgeConfigDto?: JudgeConfig;
    submitNum?: number;
    tags?: Array<string>;
    title?: string;
};
