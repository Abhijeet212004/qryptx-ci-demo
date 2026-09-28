# QryptX CI demo

A deliberately vulnerable payments service, used to demonstrate ECDAT running
as a step in GitHub Actions.

Every scan publishes to the ECDAT dashboard and fails the build when the
quality gate fails. Findings appear on the diff of a pull request through the
SARIF upload.
